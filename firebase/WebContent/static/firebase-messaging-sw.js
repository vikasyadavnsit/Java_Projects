importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');

var config = {
	apiKey : "AIzaSyCiKjG8lMYkPVRs0EoeYpg1_D8GgRGdzS8",
	authDomain : "battleramp-6236d.firebaseapp.com",
	databaseURL : "https://battleramp-6236d.firebaseio.com",
	projectId : "battleramp-6236d",
	storageBucket : "battleramp-6236d.appspot.com",
	messagingSenderId : "177406076130"
};
firebase.initializeApp(config);

const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function(payload) {
	console.log('[firebase-messaging-sw.js] Received background message ',
			payload);
	// Customize notification here
	  var title = payload.notification.title;
	  var options = {
				body : payload.notification.body,
				icon : payload.notification.icon,
				image : payload.data["gcm.notification.image"],
				data : {
					time : new Date(Date.now().toString()),
					click_action: payload.notification.click_action
					}
			  };

	return self.registration.showNotification(title, options);
	
});


self.addEventListener("notificationclick", function(event) {
	var action_click = event.notification.click_action;
	event.notification.close();
	
	
	event.waitUntil(clients.openWindow(action_click));
	
	// log send to server
	});