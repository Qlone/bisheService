// JavaScript Document
function showTree(){
var myTreeData = new Array();
myTreeData = [[0,0,"Root"],[1,0,"Tree0"],[2,0,"Tree1"],[3,1,"Tree2"],[4,1,"Tree3"],[5,1,"Tree4"],[6,3,"Tree5"],[7,2,"Tree6"]];
for(i=0;i<myTreeData.length;i++){
	var elem = document.createElement('div');
	elem.id="tree_"+myTreeData[i][0];
	elem.innerHTML= '<a href="javascript:;" onclick="javascript:itemClick(\''+myTreeData[i][0]  +'\')"><img src="../graphics/down.gif" id="img_'+ myTreeData[i][0] +'"/  border="0"><img src="../graphics/down.gif" border="0" id="folder_'+ myTreeData[i][0] +'" />' + myTreeData[i][2]+ '</a>'; 
	var elem2 = document.getElementById("tree_"+myTreeData[i][1]);
	if(elem2!=null){
		var imgElem2 = document.getElementById("img_"+myTreeData[i][1]);
		var imgElem = document.getElementById("img_"+ myTreeData[i][0]);  
		var folderElem = document.getElementById("folder_"+ myTreeData[i][1]);
		elem2.appendChild(elem);
		elem2.appendChild(elem);
		imgElem2.src="../graphics/down.gif";
		folderElem.src= "../graphics/down.gif";
		elem.className="child";
		elem.style.display="none";
		}else{
			document.body.appendChild(elem);
			elem.className="child";
			var imgElem = document.getElementById("img_"+ myTreeData[i][0]);
			var folderElem = document.getElementById("folder_"+ myTreeData[i][0]);
			elem.style.display="block";
			imgElem.src="../graphics/down.gif";
			folderElem.src = "../graphics/down.gif";
			  }
		}
}
function itemClick(id){
	 var elem= document.getElementById("tree_"+id);
	 var imgElem= document.getElementById("img_"+id);
	 var folderElem = document.getElementById("folder_"+ id);
	 if (elem.childNodes.length>1){
		 //alert(elem.childNodes.length);
		 if (elem.childNodes[1].style.display=="none"){
			 
			 for(i=1;i<elem.childNodes.length;i++){
				 elem.childNodes[i].style.display="block";
				 }
				 imgElem.src="../graphics/minus1.jpg";
				 folderElem.src = "../graphics/down.gif";
				 }else{
					 for(i=1;i<elem.childNodes.length;i++)
					    elem.childNodes[i].style.display="none";
					 imgElem.src="../graphics/minus1.jpg";
					 folderElem.src = "../graphics/down.gif";
					   }
			}
	}