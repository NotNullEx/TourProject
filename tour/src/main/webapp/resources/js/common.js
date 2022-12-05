
var pagejs = {
	setPaging : function(obj, _callback) { 
		var pageNation = "";
		if (obj.pageMaker.prev) {
			pageNation += "<li class=\"page-item\">";
			pageNation += "	<a class=\"page-link\" href =\"\">prev</a>";
			pageNation += "</li>";
		}
		
		for (var i = obj.pageMaker.startPage; i<= obj.pageMaker.endPage; i++) {
			if (obj.pageMaker.cri.page == i) {
				pageNation += "<li class=\"page-item active\" aria-current=\"page\">";
				pageNation += "	<a class=\"page-link\" href=\"javascript:pagejs.goPaging("+_callback+","+i+")\">";
				pageNation += i
				pageNation += "</a>";
				pageNation += "</li>";
			} else {
				pageNation += "<li class=\"page-item\">";
				pageNation += "	<a class=\"page-link\" href = \"javascript:pagejs.goPaging("+_callback+","+i+")\">"+i+"</a>";
				pageNation += "</li>";
			}
		}
		
		if (obj.pageMaker.next && obj.pageMaker.endPage > 0) {

			pageNation += "<li class=\"page-item\">";
			pageNation += "	<a class=\"page-link\" href = \"javascript:pagejs.goPaging("+_callback+","+ (obj.pageMaker.endPage + 1) +")\">";
			pageNation += "		next";
			pageNation += "	</a>";
			pageNation += "</li>";
		}
		$(".pagination").html(pageNation);
			
	},
	goPaging : function(_callback,page) {
		_callback(page);
	}
	
}

var stringjs = {
	isnull: function(val) {
		if(val == null || val == "undefined" || val.length == 0) return true;
		else return false;
	}
}