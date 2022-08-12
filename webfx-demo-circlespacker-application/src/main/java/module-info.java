// File managed by WebFX (DO NOT EDIT MANUALLY)

module webfx.demo.circlespacker.application {

    // Direct dependencies modules
    requires java.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires webfx.extras.led;
    requires webfx.lib.circlepacking;

    // Exported packages
    exports dev.webfx.demo.circlespacker;

    // Provided services
    provides javafx.application.Application with dev.webfx.demo.circlespacker.CirclesPackerApplication;

}