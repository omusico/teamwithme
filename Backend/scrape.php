<?php

require_once("simple_html_dom.php");

// First thing is first, we need to grab the entire web page for the hackathons for Spring 2015
$url = "https://mlh.io/seasons/s2015/events";

// Get the page
$page = file_get_html($url);

// Grab each div with a type of "event-wrapper" as this is what MLH
// uses to format their hackathons on their page.
foreach ($page->find('div[class=event-wrapper]') as $e)
{
	// Get the URL for the page for the event
	$eventURL = $e->find('a')[0]->href;

	// Grab the inner div to extract the rest of the info
	$inner = $e->find('div[class=inner]')[0];

	// Now for the logo and cover picture URL's
	$cover_pic = $inner->find('div[class=image-wrap]')[0]->find('img')[0]->src;
	$logo = $inner->find('div[class=event-logo]')[0]->find('img')[0]->src;

	// Title, date, and location of the event
	$title = $inner->find('h3')[0]->innertext;
	$date = $inner->find('p')[0]->innertext;
	$location = $inner->find('p')[1]->innertext;

	// From $date we can retrieve the start and end date of the event.
	$dateToken = explode(" ", $date);

	// Extract the date stuff from the page.
	$startDate = $dateToken[0] . " " . $dateToken[1];
	$endDate = $dateToken[0] . " " . $dateToken[3];

	echo $title . "\n";
	echo $startDate . " to " . $endDate . "\n";
	echo $location . "\n";
	echo "Logo: " . $logo . "\n";
	echo "Cover: " . $cover_pic . "\n\n";
}

?>