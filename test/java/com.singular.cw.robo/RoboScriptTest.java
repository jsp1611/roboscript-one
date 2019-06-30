package com.singular.cw.robo;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author jon
 */
public class RoboScriptTest {

    @Test
    public void highlightEmpty() {
        final String highlight = RoboScript.highlight("");
        assertThat(highlight, equalTo(""));
    }

    @Test
    public void highlightSingleF() {
        final String highlight = RoboScript.highlight("F");
        assertThat(highlight, equalTo("<span style=\"color: pink\">F</span>"));
    }

    @Test
    public void highlightMultipleF() {
        final String highlight = RoboScript.highlight("FFF");
        assertThat(highlight, equalTo("<span style=\"color: pink\">FFF</span>"));
    }

    @Test
    public void highlightSingleL() {
        final String highlight = RoboScript.highlight("L");
        assertThat(highlight, equalTo("<span style=\"color: red\">L</span>"));
    }

    @Test
    public void highlightMultipleL() {
        final String highlight = RoboScript.highlight("LLL");
        assertThat(highlight, equalTo("<span style=\"color: red\">LLL</span>"));
    }

    @Test
    public void highlightSingleR() {
        final String highlight = RoboScript.highlight("R");
        assertThat(highlight, equalTo("<span style=\"color: green\">R</span>"));
    }

    @Test
    public void highlightMultipleR() {
        final String highlight = RoboScript.highlight("RRR");
        assertThat(highlight, equalTo("<span style=\"color: green\">RRR</span>"));
    }

    @Test
    public void highlightSingleNumber() {
        final String highlight = RoboScript.highlight("1");
        assertThat(highlight, equalTo("<span style=\"color: orange\">1</span>"));
    }

    @Test
    public void highlightMultiNumber() {
        final String highlight = RoboScript.highlight("1234567890");
        assertThat(highlight, equalTo("<span style=\"color: orange\">1234567890</span>"));
    }

    @Test
    public void highlightMixedTypes() {
        final String highlight = RoboScript.highlight("(LL12(R34LF)L56F(78F)F9LR0)");
        assertThat(highlight, equalTo("(<span style=\"color: red\">LL</span>" +
                "<span style=\"color: orange\">12</span>" +
                "(<span style=\"color: green\">R</span>" +
                "<span style=\"color: orange\">34</span>" +
                "<span style=\"color: red\">L</span>" +
                "<span style=\"color: pink\">F</span>" +
                ")<span style=\"color: red\">L</span>" +
                "<span style=\"color: orange\">56</span>" +
                "<span style=\"color: pink\">F</span>" +
                "(<span style=\"color: orange\">78</span>" +
                "<span style=\"color: pink\">F</span>" +
                ")<span style=\"color: pink\">F</span>" +
                "<span style=\"color: orange\">9</span>" +
                "<span style=\"color: red\">L</span>" +
                "<span style=\"color: green\">R</span>" +
                "<span style=\"color: orange\">0</span>)"));
    }

    @Test
    public void fromDescOne() {
        final String highlight = RoboScript.highlight("F3RF5LF7");
        assertThat(highlight, equalTo("<span style=\"color: pink\">F</span>" +
                "<span style=\"color: orange\">3</span>" +
                "<span style=\"color: green\">R</span>" +
                "<span style=\"color: pink\">F</span>" +
                "<span style=\"color: orange\">5</span>" +
                "<span style=\"color: red\">L</span>" +
                "<span style=\"color: pink\">F</span>" +
                "<span style=\"color: orange\">7</span>"));
    }

    @Test
    public void fromDescTwo() {
        final String highlight = RoboScript.highlight("FFFR345F2LL");
        assertThat(highlight, equalTo("<span style=\"color: pink\">FFF</span>" +
                "<span style=\"color: green\">R</span>" +
                "<span style=\"color: orange\">345</span>" +
                "<span style=\"color: pink\">F</span>" +
                "<span style=\"color: orange\">2</span>" +
                "<span style=\"color: red\">LL</span>"));
    }
}
