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

$query = new ParseQuery('_User');
$results = $query->find();

$data = array();

foreach ($results as $result)
{
	$data[] = array(
		'name' => $result->get('name'),
		'email' => $result->get('email'),
	);
}

?>

<script>
	var userData = <?php echo json_encode($data) ?>;
</script>

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
		<script src="http://mrdoob.github.com/three.js/examples/fonts/optimer_bold.typeface.js"></script>

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
				<div class="col-xs-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Graphics</h4>
						</div>
						<div class="panel-body">
							<div id="webGL" style="margin: 0; overflow: hidden;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
