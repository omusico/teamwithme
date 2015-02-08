<?php

require 'vendor/autoload.php';
use Parse\ParseClient;
use Parse\ParseQuery;
use Parse\ParseObject;

date_default_timezone_set('UTC');

$app_id = 'v2hAXH2M27jXXzWT5ag45c72KFT4EfLuRdwSq5Ha';
$rest_key = 'tp9ikkY92F0Wyuwi3dq95j7D69hvBfA4rcoUYfOb';
$master_key = 't74hVJqag2nX7K5YaIpCgGt8Fkr8iH4eNrmRtSXJ';

ParseClient::initialize($app_id, $rest_key, $master_key);

function exportToCSV($data, $filename, $delimeter = ',')
{
	if (count($data) > 0)
	{
		$f = fopen($filename, 'w');

		fputcsv($f, array_keys($data[0]), $delimeter);

		foreach ($data as $row)
		{
			fputcsv($f, $row, $delimeter);
		}

		fclose($f);
	}
}

/**

	Hackathon

 */

$query = new ParseQuery('Hackathon');
$results = $query->find();

$data = array();

foreach ($results as $result)
{
	$data[] = array(
		'Name' => $result->get('name'),
		'Location' => $result->get('location'),
		'Start Date' => $result->get('startDate')->format('Y-m-d'),
		'End Date' => $result->get('endDate')->format('Y-m-d'),
	);
}

exportToCSV($data, 'data/hackathon.csv');

/**

	User

 */

$query = new ParseQuery('_User');
$results = $query->find();

$data = array();

foreach ($results as $result)
{
	$data[] = array(
		'Name' => $result->get('name'),
		'Email' => $result->get('email'),
	);
}

exportToCSV($data, 'data/user.csv');

/**

	Team

 */

$query = new ParseQuery('Team');
$results = $query->find();

$data = array();

foreach ($results as $result)
{
	$data[] = array(
		'Open' => $result->get('open'),
	);
}

exportToCSV($data, 'data/team.csv');

/**

	Skill

 */

$query = new ParseQuery('Skill');
$results = $query->find();

$data = array();

foreach ($results as $result)
{
	$data[] = array(
		'Name' => $result->get('name'),
	);
}

exportToCSV($data, 'data/skill.csv');

/**

	Sponsor

 */

$query = new ParseQuery('Sponsor');
$results = $query->find();

$data = array();

foreach ($results as $result)
{
	$data[] = array(
		'Name' => $result->get('name'),
	);
}

exportToCSV($data, 'data/sponsor.csv');

/**

	HackathonTeam

 */

$query = new ParseQuery('HackathonTeam');
$results = $query->find();

$data = array();

foreach ($results as $result)
{
	// $data[] = array(
	// 	'Name' => $result->get('name'),
	// );
}

/**

	TeamResponses

 */

$query = new ParseQuery('TeamResponses');
$results = $query->find();

$data = array();

foreach ($results as $result)
{
	// $data[] = array(
	// 	'Hack ID' => $result->get('HackID'),
	// 	'Team ID 1' => $result->get('TeamID1'),
	// 	'Team ID 2' => $result->get('TeamID2'),
	// );
}

?>