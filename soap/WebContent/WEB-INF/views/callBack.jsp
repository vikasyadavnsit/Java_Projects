<!DOCTYPE html>
<html lang="en">
	<head>
		<title>User Status</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://fonts.googleapis.com/css?family=Comfortaa|Jura" rel="stylesheet">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.4.0/styles/default.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.4.0/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>

	
		<style>
		 ::-webkit-scrollbar { 
    display: none; 
}
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
			flex-direction: column;
		}
		.thumb
		{
			font-family: 'Comfortaa', cursive;
			font-size: 50px;
			font-weight: 500;
			text-align: center;
		}
		.jsonMessage
		{
			margin:10px;
			border:1px solid gray;
			min-height: 50px;
		}
		.jsonMessage2
		{
			margin:10px;
			border:3px solid dodgerblue;
			border-radius: 10px;
			padding: 0px 15px;
			min-height: 50px;
			max-width: 400px;
			display: flex;
			justify-content: center;
			align-content: center;
			align-items: center;
			font-weight: 600;
			font-family: Jura;
			color: royalblue;
			font-size: 19px;
			cursor: pointer;
		}
		.popup
		{
			position: absolute;
			height: 100vh;
			width: 100%;
			top: 0px;
			left: 0px;
			z-index: 10;
			opacity: 0.2;
			align-items: center;
			justify-content: center;
			display: none;
			background-color: rgba(20, 10, 255, 0.4);
		}
		.pactive {
			animation: fadeInFromNone 0.4s ease-out;
			animation-fill-mode: forwards;
		}
		@keyframes fadeInFromNone {
		0% {
		display: none;
		opacity: 0;
		}
		1% {
		display: flex;
		opacity: 0;
		}
		100% {
		display: flex;
		opacity: 1;
		}
		}
		.popup-container
			{
			margin: 10px;	
			min-height: 200px;
			max-height: 80vh;
			width: 70%;
			border-radius: 12px;
			padding: 5px 10px;
			background-color: white;
			color: black;
		}
		.close
		{
			position: relative;
			right: -94%;
			top: 5px;
			font-size: 25px;
			font-weight: 900;
			cursor: pointer;
			width: 10px;
		}
		@media only screen and (max-width: 1200px) {
			.popup-container
				{
				width: 60%;
			}
		}
		@media only screen and (max-width: 800px) {
			.popup-container
				{
				width: 90%;
			}
		}
		.message
		{
			margin: 10px 5px;
			max-height: 70vh;
		}
		.JSON
		{
			min-height: 150px;
			max-height: 70vh;
			overflow:auto;
			border-radius: 10px;
		}
		</style>
	</head>
	<body>
		<div class="container-fluid">
			<span class="thumb">Hello ! &nbsp;&nbsp; ${name}</span>
			<div class="jsonMessage">
			</div>
			<div class="jsonMessage2" onclick="document.getElementById('openPopup').style.display = 'flex'; document.getElementById('openPopup').className += ' pactive'; ">
				Click To See Your Credentials
			</div>
		</div>
		<div id="openPopup" class="popup">
			<div class="popup-container">
				<div  class="close" onclick="document.getElementById('openPopup').style.display = 'none'; document.getElementById('openPopup').className += ' nactive'; document.getElementById('openPopup').classList.remove('pactive'); ">
					&#10006;
				</div>
				<div class="message">
					<pre><code class="JSON">
						${json}
					</code></pre>
				</div>
			</div>
		</div>
	</body>
</html>