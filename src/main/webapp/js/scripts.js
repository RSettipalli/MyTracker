
function setDefaults()
{
	document.getElementById("UserType1").checked;
	document.getElementById("bukrsList").value = 'Select';
	document.getElementById("kunnrList").value = 'Select';
	showHideSelects();
}

function showHideSelects()
{
	if (document.getElementById("UserType1").checked)
		{
			document.getElementById("kunnrList").style.visibility = 'hidden';
			document.getElementById("bukrsList").style.visibility = 'visible';
		}
	
	if (document.getElementById("UserType2").checked)
	{
		document.getElementById("bukrsList").style.visibility = 'hidden';
		document.getElementById("kunnrList").style.visibility = 'visible';
	}
	
}