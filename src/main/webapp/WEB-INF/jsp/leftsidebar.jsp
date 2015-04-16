<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script>
  $(function() {
	  

	  $('#tags').keyup(function (e) {
 		  /* $.get('search.htm', {data: $('#tags').val()}, function(result){
			  var availableTags = result;
			  $( "#tags" ).autocomplete({
				  source: availableTags
			  });
		  }).done(function() {
			    alert( "second success" );
		  })
		  .fail(function(jqXHR) {
		    alert( jqXHR.fail() );
		  })
		  .always(function() {
		    alert( "finished" );
		  });  */ 
		  
 		  $.ajax({
			  url: "search.htm", 
			  data: $('#tags').val(),
			  dataType: '/String',
			  success: function(result){
				  var availableTags = result;
				  $( "#tags" ).autocomplete({
					  source: availableTags
				  });
			  },
			  error: function( jqXHR,  textStatus,  errorThrown ){
		            alert('error '+errorThrown);
		                    }
		  });  
	  });
			  
	  
    /* var availableTags = [
      "ActionScript",
      "AppleScript",
      "Asp",
      "BASIC",
      "C",
      "C++",
      "Clojure",
      "COBOL",
      "ColdFusion",
      "Erlang",
      "Fortran",
      "Groovy",
      "Haskell",
      "Java",
      "JavaScript",
      "Lisp",
      "Perl",
      "PHP",
      "Python",
      "Ruby",
      "Scala",
      "Scheme"
    ]; */
    
  }); 
  
  function testAjax() {
	  $.ajax({
		  url : 'ajaxSearchTest.htm',
		  success : function(data) {
			  $('#result').html(data);
		  }
	  });
  }
	
  
  /* var intervalId = 0;
  intervalId = setInterval(testAjax, 3000); */
  
  </script>
  
  <!-- <script type="text/javascript">
  </script> -->
</head>
<body>
	<div id="leftSidebar">
		<!-- <form:form method="POST" class="searchform" commandName="loginForm"> -->
			<table>
				<tr>
					<td><input id="tags" type="text" placeholder="Search"></td>
				</tr>
			</table>
		<!-- </form:form> -->
		<br> <br> ${message} <br> <br>
        <div id="result"></div>
        <br>
	</div>
</body>
</html>