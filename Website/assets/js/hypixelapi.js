$(document).ready(function(){
			$('#github').click(function(){
				reDirect('github');
			});
			$('#hypixel').click(function(){
				reDirect('hypixel');
			});
			$('#javadocs').click(function(){
				reDirect('javadocs');
			});
			$('#apihelp').click(function(){
				reDirect('apihelp');
			});
		});
		
		function reDirect(Site) {
			switch(Site) {
				case 'github':
					window.location.href='https://github.com/HypixelDev/PublicAPI';
					break;
				case 'hypixel':
					window.location.href='https://hypixel.net';
					break;
				case 'javadocs':
					window.location.href='https://api.hypixel.net/javadocs';
					break;
				case 'apihelp':
					window.location.href='https://hypixel.net/forums/api-help.111/';
					break;
			}
		}