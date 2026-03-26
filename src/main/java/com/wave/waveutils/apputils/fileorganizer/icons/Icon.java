package com.wave.waveutils.apputils.fileorganizer.icons;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class Icon {

    private Icon() {}

    public static Group loadSearchIcon() {
        SVGPath path1 = new SVGPath();
        path1.setContent("m21 21-4.34-4.34");
        path1.setFill(Color.TRANSPARENT);
        path1.setStrokeWidth(2);
        path1.setStrokeLineCap(StrokeLineCap.ROUND);
        path1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path1.getStyleClass().add("icon-stroke");

        Circle circle1 = new Circle(11, 11, 8);
        circle1.setFill(Color.TRANSPARENT);
        circle1.setStrokeWidth(2);
        circle1.setStrokeLineCap(StrokeLineCap.ROUND);
        circle1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        circle1.getStyleClass().add("icon-stroke");

        Group icon = new Group(path1, circle1);
        icon.setId("search-icon");
        return icon;
    }

    public static Group loadSparklesIcon() {
        SVGPath path1 = getSvgPath();
        path1.getStyleClass().add("icon-stroke");

        SVGPath path2 = new SVGPath();
        path2.setContent("M20 2v4");
        path2.setFill(Color.TRANSPARENT);
        path2.setStrokeWidth(2);
        path2.setStrokeLineCap(StrokeLineCap.ROUND);
        path2.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path2.getStyleClass().add("icon-stroke");

        SVGPath path3 = new SVGPath();
        path3.setContent("M22 4h-4");
        path3.setFill(Color.TRANSPARENT);
        path3.setStrokeWidth(2);
        path3.setStrokeLineCap(StrokeLineCap.ROUND);
        path3.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path3.getStyleClass().add("icon-stroke");

        Circle circle1 = new Circle(4, 20, 2);
        circle1.setFill(Color.TRANSPARENT);
        circle1.setStrokeWidth(2);
        circle1.setStrokeLineCap(StrokeLineCap.ROUND);
        circle1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        circle1.getStyleClass().add("icon-stroke");

        Group icon = new Group(path1, path2, path3, circle1);
        icon.setId("sparkles-icon");
        return icon;
    }

    private static SVGPath getSvgPath() {
        SVGPath path1 = new SVGPath();
        path1.setContent("M11.017 2.814a1 1 0 0 1 1.966 0l1.051 5.558a2 2 0 0 0 1.594 1.594l5.558 1.051a1 1 0 0 1 0 1.966l-5.558 1.051a2 2 0 0 0-1.594 1.594l-1.051 5.558a1 1 0 0 1-1.966 0l-1.051-5.558a2 2 0 0 0-1.594-1.594l-5.558-1.051a1 1 0 0 1 0-1.966l5.558-1.051a2 2 0 0 0 1.594-1.594z");
        path1.setFill(Color.TRANSPARENT);
        path1.setStrokeWidth(2);
        path1.setStrokeLineCap(StrokeLineCap.ROUND);
        path1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        return path1;
    }

    public static Group loadFilesIcon() {
        SVGPath path1 = new SVGPath();
        path1.setContent("M15 2h-4a2 2 0 0 0-2 2v11a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V8");
        path1.setFill(Color.TRANSPARENT);
        path1.setStrokeWidth(2);
        path1.setStrokeLineCap(StrokeLineCap.ROUND);
        path1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path1.getStyleClass().add("icon-stroke");

        SVGPath path2 = new SVGPath();
        path2.setContent("M16.706 2.706A2.4 2.4 0 0 0 15 2v5a1 1 0 0 0 1 1h5a2.4 2.4 0 0 0-.706-1.706z");
        path2.setFill(Color.TRANSPARENT);
        path2.setStrokeWidth(2);
        path2.setStrokeLineCap(StrokeLineCap.ROUND);
        path2.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path2.getStyleClass().add("icon-stroke");

        SVGPath path3 = new SVGPath();
        path3.setContent("M5 7a2 2 0 0 0-2 2v11a2 2 0 0 0 2 2h8a2 2 0 0 0 1.732-1");
        path3.setFill(Color.TRANSPARENT);
        path3.setStrokeWidth(2);
        path3.setStrokeLineCap(StrokeLineCap.ROUND);
        path3.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path3.getStyleClass().add("icon-stroke");

        Group icon = new Group(path1, path2, path3);
        icon.setId("files-icon");
        return icon;
    }

    public static Group loadFolderOpenIcon() {
        SVGPath path1 = new SVGPath();
        path1.setContent("m6 14 1.5-2.9A2 2 0 0 1 9.24 10H20a2 2 0 0 1 1.94 2.5l-1.54 6a2 2 0 0 1-1.95 1.5H4a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h3.9a2 2 0 0 1 1.69.9l.81 1.2a2 2 0 0 0 1.67.9H18a2 2 0 0 1 2 2v2");
        path1.setFill(Color.TRANSPARENT);
        path1.setStrokeWidth(2);
        path1.setStrokeLineCap(StrokeLineCap.ROUND);
        path1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path1.getStyleClass().add("icon-stroke");

        Group icon = new Group(path1);
        icon.setId("folder-open-icon");
        return icon;
    }

    public static Group loadCircleCheckIcon() {
        Circle circle1 = new Circle(12, 12, 10);
        circle1.setFill(Color.TRANSPARENT);
        circle1.setStrokeWidth(2);
        circle1.setStrokeLineCap(StrokeLineCap.ROUND);
        circle1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        circle1.getStyleClass().add("icon-stroke");

        SVGPath path1 = new SVGPath();
        path1.setContent("m9 12 2 2 4-4");
        path1.setFill(Color.TRANSPARENT);
        path1.setStrokeWidth(2);
        path1.setStrokeLineCap(StrokeLineCap.ROUND);
        path1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path1.getStyleClass().add("icon-stroke");

        Group icon = new Group(circle1, path1);
        icon.setId("circle-check-icon");
        return icon;
    }

    public static Group loadFileStackIcon() {
        SVGPath path1 = new SVGPath();
        path1.setContent("M11 21a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1v-8a1 1 0 0 1 1-1");
        path1.setFill(Color.TRANSPARENT);
        path1.setStrokeWidth(2);
        path1.setStrokeLineCap(StrokeLineCap.ROUND);
        path1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path1.getStyleClass().add("icon-stroke");

        SVGPath path2 = new SVGPath();
        path2.setContent("M16 16a1 1 0 0 1-1 1H9a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1");
        path2.setFill(Color.TRANSPARENT);
        path2.setStrokeWidth(2);
        path2.setStrokeLineCap(StrokeLineCap.ROUND);
        path2.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path2.getStyleClass().add("icon-stroke");

        SVGPath path3 = new SVGPath();
        path3.setContent("M21 6a2 2 0 0 0-.586-1.414l-2-2A2 2 0 0 0 17 2h-3a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1z");
        path3.setFill(Color.TRANSPARENT);
        path3.setStrokeWidth(2);
        path3.setStrokeLineCap(StrokeLineCap.ROUND);
        path3.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path3.getStyleClass().add("icon-stroke");

        Group icon = new Group(path1, path2, path3);
        icon.setId("file-stack-icon");
        return icon;
    }

    public static Group loadFolderPlusIcon() {
        SVGPath path1 = new SVGPath();
        path1.setContent("M12 10v6");
        path1.setFill(Color.TRANSPARENT);
        path1.setStrokeWidth(2);
        path1.setStrokeLineCap(StrokeLineCap.ROUND);
        path1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path1.getStyleClass().add("icon-stroke");

        SVGPath path2 = new SVGPath();
        path2.setContent("M9 13h6");
        path2.setFill(Color.TRANSPARENT);
        path2.setStrokeWidth(2);
        path2.setStrokeLineCap(StrokeLineCap.ROUND);
        path2.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path2.getStyleClass().add("icon-stroke");

        SVGPath path3 = new SVGPath();
        path3.setContent("M20 20a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-7.9a2 2 0 0 1-1.69-.9L9.6 3.9A2 2 0 0 0 7.93 3H4a2 2 0 0 0-2 2v13a2 2 0 0 0 2 2Z");
        path3.setFill(Color.TRANSPARENT);
        path3.setStrokeWidth(2);
        path3.setStrokeLineCap(StrokeLineCap.ROUND);
        path3.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path3.getStyleClass().add("icon-stroke");

        Group icon = new Group(path1, path2, path3);
        icon.setId("folder-plus-icon");
        return icon;
    }

    public static Group loadFolderIcon() {
        SVGPath path1 = new SVGPath();
        path1.setContent("M20 20a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-7.9a2 2 0 0 1-1.69-.9L9.6 3.9A2 2 0 0 0 7.93 3H4a2 2 0 0 0-2 2v13a2 2 0 0 0 2 2Z");
        path1.setFill(Color.TRANSPARENT);
        path1.setStrokeWidth(2);
        path1.setStrokeLineCap(StrokeLineCap.ROUND);
        path1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        path1.getStyleClass().add("icon-stroke");

        Group icon = new Group(path1);
        icon.setId("folder-icon");
        return icon;
    }
}
