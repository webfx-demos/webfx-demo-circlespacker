// File managed by WebFX (DO NOT EDIT MANUALLY)

module webfx.demo.ledpacking.application {

    // Direct dependencies modules
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires webfx.extras.led;
    requires webfx.lib.circlepacking;

    // Exported packages
    exports dev.webfx.demo.ledpacking;

    // Provided services
    provides javafx.application.Application with dev.webfx.demo.ledpacking.LedPackingApplication;

}