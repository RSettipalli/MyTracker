
function setDefaults()
{
	//document.getElementById("UserType1").checked;
	document.getElementById("bukrsList").value = 'Select';
	document.getElementById("kunnrList").value = 'Select';
	showHideSelects();
}

function showHideSelects()
{
	if (document.getElementById("UserType1").checked)
		{
			document.getElementById("kunnrList").setAttribute('class', 'hide');
			document.getElementById("bukrsList").removeAttribute('class');
		}
	
	if (document.getElementById("UserType2").checked)
	{
		document.getElementById("bukrsList").setAttribute('class', 'hide');
		document.getElementById("kunnrList").removeAttribute('class');
	}
	
}