package nl.sogyo.mancala.MancalaUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jreuling on 27-10-2015.
 */
public class WebUI implements UI{
    String HTMLUI;

    public String asString() {
        return HTMLUI;
    }

    public WebUI()
    {
        readInitSimple();
    }

    private void readInitSimple() {
        HTMLUI = "<html><body>" +
        "<button id =\"Pit1\">4</button>" +
//        "<button id =\"Pit2\">4</button>" +
//        "<button id =\"Pit3\">4</button>" +
//        "<button id =\"Pit4\">4</button>" +
//        "<button id =\"Pit5\">4</button>" +
//        "<button id =\"Pit6\">4</button>" +
//                "<button id =\"Pit8\">4</button>" +
//                "<button id =\"Pit9\">4</button>" +
//                "<button id =\"Pit10\">4</button>" +
//                "<button id =\"Pit11\">4</button>" +
//                "<button id =\"Pit12\">4</button>" +
//                "<button id =\"Pit13\">4</button>" +
        "<script>" +
        "document.getElementById(\"Pit1\").addEventListener(\"click\",function(){buttonClick(1)});"+
//        "document.getElementById(\"Pit2\").addEventListener(\"click\",function(){buttonClick(2)});"+
//        "document.getElementById(\"Pit3\").addEventListener(\"click\",function(){buttonClick(3)});"+
//        "document.getElementById(\"Pit4\").addEventListener(\"click\",function(){buttonClick(4)});"+
//        "document.getElementById(\"Pit5\").addEventListener(\"click\",function(){buttonClick(5)});"+
//        "document.getElementById(\"Pit6\").addEventListener(\"click\",function(){buttonClick(6)});"+
//
//        "document.getElementById(\"Pit8\").addEventListener(\"click\",function(){buttonClick(8)});"+
//        "document.getElementById(\"Pit9\").addEventListener(\"click\",function(){buttonClick(9)});"+
//        "document.getElementById(\"Pit10\").addEventListener(\"click\",function(){buttonClick(10)});"+
//        "document.getElementById(\"Pit11\").addEventListener(\"click\",function(){buttonClick(11)});"+
//        "document.getElementById(\"Pit12\").addEventListener(\"click\",function(){buttonClick(12)});"+
//        "document.getElementById(\"Pit13\").addEventListener(\"click\",function(){buttonClick(13)});"+

        "function buttonClick(int pit)" +
        "{" +
            "document.getElementByID(\"Pit1\").innerHTML = 3" +
            //Make Call to corresponding pit
            "var xhttp = new XMLHttpRequest();" +
            "xhttp.open(\"POST\",\"/mancala\", false);" +
            //"xhttp.setRequestHeader(\"PitToPlay\",pit)" +
            "xhttp.send(\"1\");" +
//            "refreshBoard(xhttp);" +
        "}" +
//
//        "function refreshBoard(XMLHttpRequest xhttp)" +
//       " {" +
//            "var stoneRespone = parseResponse(xhttp);" +
//            "for (i = 1; i <= 14 ; i++){" +
//                "document.getElementById(stoneRespone[i]).innerHTML = stoneRespone(i+14);" +
//            "}" +
//        "}" +
//
//        "function parseResponse(XMLHttpRequest xhttp)" +
//        "{" +
//            "var response = xhttp.responseText;" +
//            "var responseStones = [\"Empty Element\"];" +
//            "responseStones.length = 29;" +
//            "for (int i = 1;i<=14;i++)" +
//            "{" +
//                "responseStones[i] = response.substring(0,response.indexOf(\"=\"));" +
//                "responseStones[i+14]=response.substring(response.indexOf(\"=\")+2,response.indexOf(\";\"));" +
//                "response = response.substring(response.indexOf(\";\")+2);" +
//            "}" +
//            "return responseStones;" +
//        "}" +
        "</script></html></body>";


//               HTMLUI = "<button id=\"buttonTest\" type=\"button\" onclick=\"changeText()\">Click Me!</button>" +
//                    "<p>This example uses the addEventListener() method to attach a click event to a button.</p>" +
//                    "<button id=\"myBtn\">Try it</button>" +
//                    "<p id=\"demo\">Let AJAX change this text.</p>" +
//                    "<button type=\"button\" onclick=\"loadDoc()\">Change Content</button>" +
//                    "<script>" +
//                    "document.getElementById(\"myBtn\").addEventListener(\"click\", testFunction);" +
//                    "function testFunction(){" +
//                    "alert(\"It works!\");" +
//                    "}" +
//                    "function changeText(){" +
//                    "document.getElementById(\"buttonTest\").innerHTML = \"I have been clicked\";}"+
//                    "function loadDoc(){" +
//                    "var xhttp = new XMLHttpRequest();" +
//                    "xhttp.open(\"POST\", \"/mancala\", false);" +
//                    "xhttp.send(\"1\");" +
//                    "document.getElementById(\"demo\").innerHTML = xhttp.responseText" +
//                    "}" +
//                    "</script>";


    }

    private void readInit() {
        String rootDir = System.getProperty("user.dir");
        FileReader fr = null;
        try {
            fr = new FileReader(rootDir + "/java/nl/sogyo/mancala/MancalaUI/WebUIInit.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        BufferedReader completeFile = new BufferedReader(fr);
        String s;
        ArrayList<String> textLines = new ArrayList<>();
        try {
            while ((s = completeFile.readLine())!= null )
            {
                textLines.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        compressTextLines(textLines);
    }

    private void compressTextLines(ArrayList<String> textLines) {
        StringBuilder compressedStringBuilder = new StringBuilder("");
        for (String line:textLines)
        {
            compressedStringBuilder.append(line + " ");
        }
        HTMLUI = compressedStringBuilder.toString();
    }



    @Override
    public void refreshBoard() {
        //
    }

    @Override
    public void showResult() {
        //
    }
}
