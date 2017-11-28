package org.mose.boot.dao;

/**
 * Description: 分页模型
 *
 * 根据给定参数生成基于metronic的分页控件
 *
 * 控件包含：第一页、上一页、可选择分页、下一页和最后一页
 *
 * 当maxPageNumberCount为偶数时，系统约定左右侧页码均富余时，左侧比右侧多显示一个页码
 * 例如2,3,4,5,6,7,8 可选择页为6个，其中6为当前页，则6左侧（2,3,4）较右侧（7,8）多页一个可选择页码
 *
 * @Author: 靳磊
 * @Date: 2017/8/20:22
 */
public abstract class AbstractPagination {
    /**
     * 链接地址
     */
    protected String url;
    /**
     * 总行数
     */
    protected int rowCount;
    /**
     * 每页行数，默认20行每页
     */
    protected int pageRowCount = 20;
    /**
     * 当前页码，默认为1
     */
    protected int pageNumber = 1;
    /**
     * 最多可选择页码数，默认为5
     */
    protected int maxPageNumberCount = 5;

    /**
     * 根据给定的参数生成分页Html
     */
    public abstract String createHtml();

    /**
     * 获得总页数，最小为1
     *
     * @return
     */
    protected int getPageCount() {
        return Math.max(1, (rowCount % pageRowCount) == 0 ? (rowCount / pageRowCount) : (rowCount / pageRowCount + 1));
    }

    /**
     * 可选择页码，前一页页码
     */
    protected int getPreviousPageNumber() {
        int previousPageNumber = Math.max(1, (pageNumber - 1));
        return previousPageNumber;
    }

    /**
     * 可选择页码，开始页码
     */
    protected int getBeginPageNumber(int pageCount) {
        int averagePageNumberCount = maxPageNumberCount / 2;//基于当前页，可选择页平均数量
        int beginPageNumber = Math.max(1, (pageNumber - averagePageNumberCount)); // 根据可选择页平均数量计算开始页码，如果小于1，则取取1
        int difference = averagePageNumberCount - (pageCount - pageNumber);// 计算当前页码与最大之间的差额与可选择页平均数量之间的差额
        // 如果这个差额>0，说明当前页右侧可选择页码数不足，为了能够显示maxPageNumberCount
        // 需要将这个差额补充到开始页码，即开始页码应向左移动，移动的距离为这个差额，且不小于最小页码数1
        if (difference > 0) {
            beginPageNumber = Math.max(1, beginPageNumber - difference);
        }

        // 当maxPageNumberCount为偶数时，系统约定左右侧页码均富余时，左侧比右侧多显示一个页码，如果左侧页码富余，且可选择页码数为偶数，则开始页码少増加1页
        // 与结束页码数处理呼应
        if (maxPageNumberCount % 2 == 0 && (pageNumber - beginPageNumber) > averagePageNumberCount) {
            beginPageNumber = beginPageNumber + 1;
        }

        return beginPageNumber;
    }

    /**
     * 可选择页码，后一页页码
     */
    protected int getNextPageNumber(int pageCount) {
        return Math.min(pageCount, (pageNumber + 1));
    }

    /**
     * 可选择页码，结束页码
     */
    protected int getEndPageNumber(int pageCount) {
        int averagePageNumberCount = maxPageNumberCount / 2;//基于当前页，可选择页平均数量
        int endPageNumber = Math.min(pageCount, (pageNumber + averagePageNumberCount)); // 根据可选择页平均数量计算开始页码，如果大于最大页数，则取最大页数
        int difference = averagePageNumberCount - (pageNumber - 1);// 计算当前页码与最小页码（即1）之间的差额与可选择页平均数量之间的差额
        // 如果这个差额>0，说明当前页左侧可选择页码数不足，为了能够显示maxPageNumberCount
        // 需要将这个差额补充到结束页码，即结束页码应向右移动，移动的距离为这个差额，且不超过最大页码数
        if (difference > 0) {
            endPageNumber = Math.min(pageCount, endPageNumber + difference);
        }

        // 当maxPageNumberCount为偶数时，系统约定左右侧页码均富余时，左侧比右侧多显示一个页码，如果右侧页码富余，且可选择页码数为偶数，则结束页码少増加1页
        // 与开始页码数处理呼应
        if (maxPageNumberCount % 2 == 0 && (endPageNumber - pageNumber) >= averagePageNumberCount) {
            endPageNumber -= 1;
        }
        return endPageNumber;
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
