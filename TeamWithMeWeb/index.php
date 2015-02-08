<?php

require '../Backend/vendor/autoload.php';
use Parse\ParseClient;
use Parse\ParseQuery;
use Parse\ParseObject;

date_default_timezone_set('UTC');

$app_id = 'v2hAXH2M27jXXzWT5ag45c72KFT4EfLuRdwSq5Ha';
$rest_key = 'tp9ikkY92F0Wyuwi3dq95j7D69hvBfA4rcoUYfOb';
$master_key = 't74hVJqag2nX7K5YaIpCgGt8Fkr8iH4eNrmRtSXJ';

ParseClient::initialize($app_id, $rest_key, $master_key);

?>

<!DOCTYPE>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="language" content="en" />

		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

		<!-- Script -->
		<script type='text/javascript' src="js/jquery.min.js"></script>
		<script type='text/javascript' src="js/three.min.js"></script>
		<script type='text/javascript' src="js/request_animation_frame.js"></script>
		<script type='text/javascript' src="js/graphics.js"></script>

		<title>Team With Me</title>
	</head>
	<body>
		<div class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand">Team With Me</a>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="main-container">
				<div class="col-xs-8">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Graphics</h4>
						</div>
						<div class="panel-body">
							<div id="webGL" style="margin: 0; overflow: hidden;"></div>
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Data Plots</h4>
						</div>
						<div class="panel-body">
							To Do
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
