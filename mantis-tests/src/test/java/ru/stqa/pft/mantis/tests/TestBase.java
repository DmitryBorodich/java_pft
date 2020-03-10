package ru.stqa.pft.mantis.tests;


import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();

    }
    public static boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {

        boolean isFixed = false;
        Set<Project> projects = app.soap().getProject();
        Issue issue = new Issue().withId(issueId);
        MantisConnectPortType mc = app.soap().getMantisConnect();
        ObjectRef status =  mc.mc_issue_get("administrator","root", BigInteger.valueOf(issueId)).getStatus();
        if (status.getName().equals("fixed")) {
            isFixed = true;
        }
        return isFixed;
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public static boolean isIssueOpen2(int issueId2) throws IOException, ServiceException {

        boolean isFixed = false;
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=500")).returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues =  parsed.getAsJsonObject().get("issues");
        Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
        Issue issue1= issue.iterator().next().withId(issueId2);
        if (!issue1.getState_name().equals("Open")){
            isFixed = false;
        }
        return isFixed;
    }

    private static Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public static void skipIfNotFixed2(int issueId) throws IOException, ServiceException {
        if (isIssueOpen2(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
