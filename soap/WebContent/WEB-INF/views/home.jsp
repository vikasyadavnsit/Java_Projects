<!DOCTYPE html>
<html lang="en">
	<head>
		<title>OAuth Implementation</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" >
		<style>
		*{
			margin: 0px;
			padding: 0px;
			box-sizing: border-box;
		}
			.container-fluid
			{
				min-height: 100vh;
				width: 100%;
				background-color: white;
				display: flex;
				align-items: center;
				justify-content: center;
				align-content: center;
				flex-wrap: wrap;
			}

			.block
			{
				margin:15px 8px;
				background-color: white;
				box-shadow: 0 .5rem 1rem rgba(0,0,0,.15)!important;
				height: 200px;
				width: 200px;
				border : 1px solid whitesmoke;
				border-radius: 5px;
				transition: all 0.5s ease-in-out;
				display: flex;
				align-items: center;
				justify-content: center;
				flex-direction: column;
			}
			.block:hover
			{
				transform: translateY(-10px);
			}
		</style>
	</head>
	<body>
		<div class="container-fluid">
			<div onclick="location.href = './facebook';" class="block" style="background-color: #4867AA;">
				<i style="color:white;" class="fa-5x fab fa-facebook"></i>
			</div>
			<div onclick="location.href = './google';"class="block" style="background-color: white">
				<i style="color:orangered;" class="fa-5x fab fa-google"></i>
				
			</div>
			<div onclick="location.href = './github';"class="block" style="background-color: whitesmoke;">
				<i  class="fa-5x fab fa-github"></i>
				
			</div>
			<div onclick="location.href = './linkedin';" class="block" style="background-color: dodgerblue;">
				<i style="color:white;" class="fa-5x fab fa-linkedin"></i>
				
			</div>
		</div>
	</body>
</html>