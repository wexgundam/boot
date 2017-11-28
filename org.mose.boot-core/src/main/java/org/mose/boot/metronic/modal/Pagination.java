package org.mose.boot.metronic.modal;

import org.mose.boot.dao.AbstractPagination;

/**
 * Description: 基于Metronic的分页模型
 *
 * @Author: 靳磊
 * @Date: 2017/8/20:22
 */
public class Pagination extends AbstractPagination {
    /**
     * 根据给定的参数生成分页Html
     */
    @Override
    public String createHtml() {
        StringBuffer stringBuffer = new StringBuffer();
        int pageCount = getPageCount();// 总页数
        int previousPageNumber = getPreviousPageNumber();// 可选择页码，前一页页码
        int beginPageNumber = getBeginPageNumber(pageCount);// 可选择页码，开始页码
        int nextPageNumber = getNextPageNumber(pageCount);// 可选择页码，后一页页码
        int endPageNumber = getEndPageNumber(pageCount);// 可选择页码，结束页码
        stringBuffer.append("<div class=\"row\">");
        stringBuffer.append("<div class=\"col-xs-12\">");
        stringBuffer.append("<div class=\"m-grid\">");
        stringBuffer.append("<div class=\"m-grid-row\">");

        stringBuffer.append("<div class=\"m-grid-col m-grid-col-middle m-grid-col-right m-grid-col-xs-11\">");

        stringBuffer.append("<ul class=\"pagination\">");

        // 第一页
        if (1 == pageNumber) {
            stringBuffer.append("<li").append(" class=\"disabled\" ").append(">");
        } else {
            stringBuffer.append("<li>");
        }
        stringBuffer.append("<a href = \"").append(url).append("?pageNumber=").append(1).append("\" >");
        stringBuffer.append("<i class=\"fa fa-angle-double-left\" ></i >");
        stringBuffer.append("</a >");
        stringBuffer.append("</li >");

        // 上一页
        if (previousPageNumber == pageNumber) {
            stringBuffer.append("<li").append(" class=\"disabled\" ").append(">");
        } else {
            stringBuffer.append("<li>");
        }
        stringBuffer.append("<a href = \"").append(url).append("?pageNumber=").append(previousPageNumber).append("\" >");
        stringBuffer.append("<i class=\"fa fa-angle-left\" ></i >");
        stringBuffer.append("</a >");
        stringBuffer.append("</li >");

        for (int index = beginPageNumber; index <= endPageNumber; index++) {
            if (index == pageNumber) {
                stringBuffer.append("<li").append(" class=\"active\" ").append(">");
            } else {
                stringBuffer.append("<li>");
            }
            stringBuffer.append("<a href = \"").append(url).append("?pageNumber=").append(index).append("\" >");
            stringBuffer.append(index);
            stringBuffer.append("</a >");
            stringBuffer.append("</li >");
        }

        // 下一页
        if (nextPageNumber == pageNumber) {
            stringBuffer.append("<li").append(" class=\"disabled\" ").append(">");
        } else {
            stringBuffer.append("<li>");
        }
        stringBuffer.append("<a href = \"").append(url).append("?pageNumber=").append(nextPageNumber).append("\" >");
        stringBuffer.append("<i class=\"fa fa-angle-right\" ></i >");
        stringBuffer.append("</a >");
        stringBuffer.append("</li >");

        // 最后一页
        if (pageCount == pageNumber) {
            stringBuffer.append("<li").append(" class=\"disabled\" ").append(">");
        } else {
            stringBuffer.append("<li>");
        }
        stringBuffer.append("<a href = \"").append(url).append("?pageNumber=").append(1).append("\" >");
        stringBuffer.append("<i class=\"fa fa-angle-double-right\" ></i >");
        stringBuffer.append("</a >");
        stringBuffer.append("</li >");

        stringBuffer.append("</ul>");// End UL

        stringBuffer.append("</div>");// End div m-grid-col-xs-11

        stringBuffer.append("<div class=\"m-grid-col m-grid-col-middle m-grid-col-center m-grid-col-xs-1\">");
        stringBuffer.append("共有<strong>").append(rowCount).append("</strong>条");
        stringBuffer.append("<strong>").append(pageCount).append("</strong>页数据");
        stringBuffer.append("</div>");// End div m-grid-col-xs-3

        stringBuffer.append("</div>");// End div m-grid-row
        stringBuffer.append("</div>");// End div m-grid
        stringBuffer.append("</div>");// End div col-xs-12
        stringBuffer.append("</div>");// End div row

        return stringBuffer.toString();
    }
}
