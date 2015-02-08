<?php

require_once('simple_html_dom.php');

require 'vendor/autoload.php';
use Parse\ParseClient;
use Parse\ParseQuery;
use Parse\ParseObject;
use Parse\ParseUser;

date_default_timezone_set('UTC');

$app_id = 'v2hAXH2M27jXXzWT5ag45c72KFT4EfLuRdwSq5Ha';
$rest_key = 'tp9ikkY92F0Wyuwi3dq95j7D69hvBfA4rcoUYfOb';
$master_key = 't74hVJqag2nX7K5YaIpCgGt8Fkr8iH4eNrmRtSXJ';

ParseClient::initialize($app_id, $rest_key, $master_key);

$url = 'http://www.fakenamegenerator.com/advanced.php?t=country&n%5B%5D=us&c%5B%5D=us&gen=50&age-min=19&age-max=85';

$userCount = 8;

for ($i = 0; $i < $userCount; $i++)
{
	// Get a random from the generator.
	$webName = file_get_html($url)->find('div[class=info]')[0]->find('div[class=address]')[0]->find('h3')[0]->innertext;
	$nameToken = explode(' ', $webName);

	$username = strtolower(substr($nameToken[0], 0, 1) . $nameToken[2]);
	$name = $nameToken[0] . ' ' . $nameToken[2];

	$user = new ParseUser();

	$user->set('username', $username);
	$user->set('email', $username . '@stetson.edu');
	$user->set('password', 'password');
	$user->set('name', $name);
	$user->signUp();
}

?>