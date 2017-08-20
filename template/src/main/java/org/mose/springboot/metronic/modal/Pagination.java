package org.mose.springboot.metronic.modal;

/**
 * Description: 分页模型
 *
 * @Author: 靳磊
 * @Date: 2017/8/20:22
 */
public class Pagination {
    /**
     * 链接地址
     */
    private String url;
    /**
     * 总行数
     */
    private int rowCount;
    /**
     * 每页行数，默认20行每页
     */
    private int pageRowCount;
    /**
     * 当前页码
     */
    private int pageNumber;
    /**
     * 最多可选择页码数，默认为5
     */
    private int maxPageNumberCount = 5;

    /**
     * 根据给定的参数生成分页Html
     */
    public String createHtml() {
        StringBuffer stringBuffer = new StringBuffer();
        int pageCount = getPageCount();// 总页数
        int previousPageNumber = getPreviousPageNumber();// 可选择页码，前一页页码
        int beginPageNumber = getBeginPageNumber();// 可选择页码，开始页码
        int nextPageNumber = getNextPageNumber(pageCount);// 可选择页码，后一页页码
        int endPageNumber = getEndPageNumber(pageCount);// 可选择页码，结束页码
        stringBuffer.append("<div class=\"row\">");
        stringBuffer.append("<div class=\"col-xs-12\">");
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

        // 前一页
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

        // 后一页
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

        return stringBuffer.toString();
    }

    /**
     * 获得总页数
     *
     * @return
     */
    private int getPageCount() {
        return (rowCount % pageRowCount) == 0 ? (rowCount / pageRowCount) : (rowCount / pageRowCount + 1);
    }

    /**
     * 可选择页码，前一页页码
     */
    private int getPreviousPageNumber() {
        return Math.max(1, (pageNumber - 1));
    }

    /**
     * 可选择页码，开始页码
     */
    private int getBeginPageNumber() {
        return Math.max(1, (pageNumber - maxPageNumberCount / 2));
    }

    /**
     * 可选择页码，后一页页码
     */
    private int getNextPageNumber(int pageCount) {
        return Math.min(pageCount, (pageNumber + 1));
    }

    /**
     * 可选择页码，结束页码
     */
    private int getEndPageNumber(int pageCount) {
        return Math.min(pageCount, (pageNumber + maxPageNumberCount / 2));
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "url='" + url + '\'' +
                ", rowCount=" + rowCount +
                ", pageRowCount=" + pageRowCount +
                ", pageNumber=" + pageNumber +
                ", maxPageNumberCount=" + maxPageNumberCount +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageRowCount() {
        return pageRowCount;
    }

    public void setPageRowCount(int pageRowCount) {
        this.pageRowCount = pageRowCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getMaxPageNumberCount() {
        return maxPageNumberCount;
    }

    public void setMaxPageNumberCount(int maxPageNumberCount) {
        this.maxPageNumberCount = maxPageNumberCount;
    }
}
