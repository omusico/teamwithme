<?php

require_once('simple_html_dom.php');

require 'vendor/autoload.php';
use Parse\ParseClient;
use Parse\ParseQuery;
use Parse\ParseObject;

date_default_timezone_set('UTC');

$app_id = 'v2hAXH2M27jXXzWT5ag45c72KFT4EfLuRdwSq5Ha';
$rest_key = 'tp9ikkY92F0Wyuwi3dq95j7D69hvBfA4rcoUYfOb';
$master_key = 't74hVJqag2nX7K5YaIpCgGt8Fkr8iH4eNrmRtSXJ';

ParseClient::initialize($app_id, $rest_key, $master_key);

// First thing is first, we need to grab the entire web page for the hackathons for Spring 2015
$url = 'https://mlh.io/seasons/s2015/events';

// Get the page
$page = file_get_html($url);

// Grab each div with a type of "event-wrapper" as this is what MLH
// uses to format their hackathons on their page.
foreach ($page->find('div[class=event-wrapper]') as $e)
{
	// Get the URL for the page for the event
	$eventUrl = $e->find('a')[0]->href;

	// Grab the inner div to extract the rest of the info
	$inner = $e->find('div[class=inner]')[0];

	// Now for the logo and cover picture URL's
	$coverUrl = $inner->find('div[class=image-wrap]')[0]->find('img')[0]->src;
	$logoUrl = $inner->find('div[class=event-logo]')[0]->find('img')[0]->src;

	// Name, date, and location of the event
	$name = $inner->find('h3')[0]->innertext;
	$date = $inner->find('p')[0]->innertext;
	$location = $inner->find('p')[1]->innertext;

	// From $date we can retrieve the start and end date of the event.
	$dateToken = explode(' - ', $date);
	$startToken = explode(' ', $dateToken[0]);
	$endToken = explode(' ', $dateToken[1]);

	// Set the start date.
	$startDate = $dateToken[0];

	if (count($endToken) == 1)
	{
		// If the end month is missing, use the start month for the end month.
		$endDate = $startToken[0] . ' ' . $endToken[0];
	}
	else
	{
		// Set the end date.
		$endDate = $dateToken[1];
	}

	// Query for a Hackathon with a matching name.
	$query = new ParseQuery('Hackathon');
	$query->equalTo('name', $name);
	$results = $query->find();

	// Create a new Hackathon if query result is empty.
	$hackathon = (empty($results)) ? ParseObject::create('Hackathon') : $results[0];

	$hackathon->set('name', $name);
	$hackathon->set('startDate', new DateTime($startDate));
	$hackathon->set('endDate', new DateTime($endDate));
	$hackathon->set('location', $location);
	$hackathon->set('logoUrl', $logoUrl);
	$hackathon->set('coverUrl', $coverUrl);
	$hackathon->set('websiteUrl', $eventUrl);

	// Update Hackathon table row.
	$hackathon->save();
}

?>