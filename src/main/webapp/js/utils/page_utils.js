//生成分页工具
function buildPageHtml(currPage, limit, total, funName) {
    var totalPage = parseInt(total / limit);
    if (total % limit > 0) {
        totalPage++;
    }
    if (totalPage == 0) {
        totalPage++;
    }
    var html = "";
	//页码全览
	html += '<div class="dataTables_info">第 '+currPage+' 页， 每页 '+limit+' 条 ，共 '+total+' 条</div>';
	html += '<div class="dataTables_paginate paging_simple_numbers">';
	//上一页
	if (currPage == 1) {
		html += '<a class="paginate_button previous disabled" href="javascript:void(0);">上一页</a>';
	}
	else{
		html += '<a class="paginate_button previous" href="javascript:'+funName+'(' + (currPage-1) + ')">上一页</a>';
	}
	html += '<span>';
	//中间页码
    if (totalPage <= 6) {
        for (var i = 1; i <= totalPage; i++) {
            html += buildPageNumHtml(i, funName, currPage);
        }
    }
    else {
        if (currPage <= 3) {
            for (var i = 1; i <= currPage + 2; i++) {
                html += buildPageNumHtml(i, funName, currPage);
            }
            html += '<span>...</span>';
            html += buildPageNumHtml(totalPage, funName, currPage);
        }
        else if (currPage >= (totalPage - 3)) {
            html += buildPageNumHtml(1, funName, currPage);
            html += '<span>...</span>';
            for (var i = currPage - 2; i <= totalPage; i++) {
                html += buildPageNumHtml(i, funName, currPage);
            }
        }
        else {
            html += buildPageNumHtml(1, funName, currPage);
            html += '<span>...</span>';
            for (var i = currPage - 2; i <= currPage + 2; i++) {
                html += buildPageNumHtml(i, funName, currPage);
            }
            html += '<span>...</span>';
            html += buildPageNumHtml(totalPage, funName, currPage);
        }
    }
	html += '</span>';
	//下一页
	if (currPage == totalPage) {
		html += '<a class="paginate_button next disabled" href="javascript:void(0);">下一页</a>';
    }
	else{
		html += '<a class="paginate_button next" href="javascript:'+funName+'(' + (currPage+1) + ')">下一页</a>';
	}
	
	html += '</div>';
    return html;
}

//生成页码(私有方法)
function buildPageNumHtml(pageNum, funName, currPage) {
    var html = '';
    if (pageNum == currPage) {
        html += '<a class="paginate_button current" href="javascript:void(0);">' + (pageNum) + '</a>';
    }
    else {
    	html += '<a class="paginate_button" href="javascript:'+funName+'(' + pageNum + ')">' + (pageNum) + '</a>';
    }
    return html;
}
