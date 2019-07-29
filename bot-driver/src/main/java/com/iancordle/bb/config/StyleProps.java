package com.iancordle.bb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "style")
public class StyleProps {
    private RGB runPostColor;
    private String runIcon;

    public RGB getRunPostColor() {
        return runPostColor;
    }

    public void setRunPostColor(RGB runPostColor) {
        this.runPostColor = runPostColor;
    }

    public String getRunIcon() {
        return runIcon;
    }

    public void setRunIcon(String runIcon) {
        this.runIcon = runIcon;
    }

    public static class RGB {
        private int r;
        private int g;
        private int b;

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g = g;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }
}
