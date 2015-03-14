jQuery.noConflict();

	/*--- Onload settings  ---*/
	jQuery(document).ready(function() {
		// expand collapse script
		jQuery(".toggle-container").show();
		
		jQuery(".triggeractive").click(function(){
			jQuery('.triggeractive').addClass("trigger");
			jQuery('.trigger').removeClass("triggeractive");
			jQuery(this).next(".toggle-container").slideToggle("slow,");
		});
		
		jQuery(".trigger").click(function(){
			jQuery('.trigger').addClass("triggeractive");
			jQuery('.triggeractive').removeClass("trigger");
			jQuery(this).next(".toggle-container").slideToggle("slow,");
		});
			
	});
	