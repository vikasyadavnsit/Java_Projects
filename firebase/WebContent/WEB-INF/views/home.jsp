<!DOCTYPE html>
<html lang="en">
<head>
<title>Firebase Web Push Notification</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="manifest"
	href="<%=request.getContextPath()%>/static/manifest.json">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/test.css">

<script src="https://www.gstatic.com/firebasejs/5.8.5/firebase.js"></script>
<script>
  // Initialize Firebase
  
  var config = {
    apiKey: "AIzaSyCiKjG8lMYkPVRs0EoeYpg1_D8GgRGdzS8",
    authDomain: "battleramp-6236d.firebaseapp.com",
    databaseURL: "https://battleramp-6236d.firebaseio.com",
    projectId: "battleramp-6236d",
    storageBucket: "battleramp-6236d.appspot.com",
    messagingSenderId: "177406076130"
  };
  firebase.initializeApp(config);

  const messaging = firebase.messaging();


   messaging.requestPermission().then(function() {
	   getRegisterToken()
   }).catch(function(err) {
	  console.log('Unable to get permission to notify.', err);
	});

 function getRegisterToken()
 {
	  navigator.serviceWorker.register('./static/firebase-messaging-sw.js')
	  .then((registration) => {
	    messaging.useServiceWorker(registration);
	  });
	  	 
	 messaging.getToken().then(function(currentToken) {
		  if (currentToken) {
		    sendTokenToServer(currentToken);
		    console.log(currentToken);
		  } else {
		    console.log('No Instance ID token available. Request permission to generate one.');
		    setTokenSentToServer(false);
		  }
		}).catch(function(err) {
		  console.log('An error occurred while retrieving token. ', err);
		  setTokenSentToServer(false);
		});

	 }

   
 	  function sendTokenToServer(currentToken) {
	    if (!isTokenSentToServer()) {
	      console.log('Sending token to server...');
	      setTokenSentToServer(true);
	    } else {
	      console.log('Token already sent to server so won\'t send it again ' +
	          'unless it changes');
	    }
	  }
	  function isTokenSentToServer() {
	    return window.localStorage.getItem('sentToServer') === '1';
	  }
	  function setTokenSentToServer(sent) {
	    window.localStorage.setItem('sentToServer', sent ? '1' : '0');
	  }

	  messaging.onMessage(function(payload) {
		  console.log('Message received. ', payload);
		  var title = payload.notification.title;
		  var options = {
					body : payload.notification.body,
					icon : payload.notification.icon,
		//			image : payload.data["gcm.notification.image"],
					data : {
						time : new Date(Date.now().toString()),
						click_action: payload.notification.click_action
						}
				  };
		  var myNotification = new Notification(title,options);
		  
		});

</script>

<script>
 const database = firebase.database();

 writeUserData("vikas","vikas yadav",'vikas@gmail.com');

 function writeUserData(pushId, name, email) {
	  firebase.database().ref('pushId/' + pushId).set({
	    username: name,
	    email: email,
	  }, function(error){
		if(error)	
 			console.log('error occured');
	  	else
		  console.log('success');
	  }
	  );
	}

	 var readDatabase = firebase.database().ref('pushId');
	 readDatabase.on('value', function(snapshot) {
	   console.log(snapshot.val());
	 });

	
</script>

</head>
<body>
	<div class="container-fluid text-center test">Fire Base Web
		PushNotification</div>
</body>
</html>