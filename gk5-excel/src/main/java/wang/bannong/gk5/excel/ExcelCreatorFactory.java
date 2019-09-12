package wang.bannong.gk5.excel;

import wang.bannong.gk5.excel.style.ExcelCreatorSingleSheet;

/**
 * Created by wang.bannong on 2018/6/21 下午3:29
 */
public final class ExcelCreatorFactory {

    private static ExcelCreatorFactory creatorFactory = null;

    private ExcelCreator creator;

    private ExcelCreatorFactory(){}

    public synchronized static ExcelCreatorFactory instance() {
        if (null == creatorFactory) {
            creatorFactory = new ExcelCreatorFactory();
        }
        return creatorFactory;
    }

    public ExcelCreator getCreator(ExcelStyle style) {
        switch (style) {
            case single_sheet:
                creator = new ExcelCreatorSingleSheet();
                break;
            default:
                creator = new ExcelCreatorSingleSheet();
        }

        return creator;
    }

}
