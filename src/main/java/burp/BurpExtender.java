package burp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: fakeIP
 * Date:2021/5/21 上午11:07
 * @author CoolCat
 * @version 1.0.0
 * Github:https://github.com/TheKingOfDuck
 * When I wirting my code, only God and I know what it does. After a while, only God knows.
 */
public class BurpExtender implements IBurpExtender, IContextMenuFactory,IIntruderPayloadGeneratorFactory, IIntruderPayloadGenerator, IHttpListener{
    public static IExtensionHelpers helpers;
    private String PLUGIN_NAME = "burpFakeIP";
    private String VERSION = "1.1";
    public static PrintWriter stdout;

    @Override
    public void registerExtenderCallbacks(final IBurpExtenderCallbacks callbacks) {
        helpers = callbacks.getHelpers();

        stdout = new PrintWriter(callbacks.getStdout(), true);
        String banner = "[+] %s %s is loaded...\n" +
                "[+] ####################################\n" +
                "[+]    Anthor: CoolCat\n" +
                "[+]    Blog:   https://blog.thekingofduck.com/\n" +
                "[+]    Github: https://github.com/TheKingOfDuck\n" +
                "[+] ####################################\n" +
                "[+] Enjoy it~";
        stdout.println(String.format(banner,PLUGIN_NAME,VERSION));

        //注册菜单
        callbacks.registerContextMenuFactory(this);
        callbacks.registerIntruderPayloadGeneratorFactory(this);
        callbacks.setExtensionName(PLUGIN_NAME);
        callbacks.registerHttpListener(this);

    }

    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation iContextMenuInvocation) {
        List<JMenuItem> menus = new ArrayList();
        JMenu menu = new JMenu(PLUGIN_NAME);

        JMenuItem custom = new JMenuItem("customIP");
        JMenuItem localhost = new JMenuItem("127.0.0.1");
        JMenuItem random = new JMenuItem("randomIP");
        JMenuItem autoXFF = new JMenuItem("AutoXFF");

        menu.add(custom);
        menu.add(localhost);
        menu.add(random);
        menu.add(autoXFF);

        if(iContextMenuInvocation.getInvocationContext() != IContextMenuInvocation.CONTEXT_MESSAGE_EDITOR_REQUEST){
            return menus;
        }
        custom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String ip = JOptionPane.showInputDialog("Pls input ur ip:");
                Utils.addfakeip(iContextMenuInvocation,ip);
            }
        });

        localhost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Utils.addfakeip(iContextMenuInvocation,"127.0.0.1");
            }
        });

        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Utils.addfakeip(iContextMenuInvocation,Utils.getRandomIp());
            }
        });

        autoXFF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String xff = JOptionPane.showInputDialog("Pls input ur XFF header name:",Config.AUTOXFF);
                String xffvalue = JOptionPane.showInputDialog("Pls input ur XFF header value:",Config.AUTOXFFVALUE);
                Config.AUTOXFF = xff;
                Config.AUTOXFFVALUE = xffvalue;
            }
        });

        menus.add(menu);
        return menus;
    }


    @Override
    public boolean hasMorePayloads() {
        return true;
    }

    @Override
    public byte[] getNextPayload(byte[] bytes) {
        String payload = Utils.getRandomIp();
        return payload.getBytes();
    }

    @Override
    public void reset() {

    }

    @Override
    public String getGeneratorName() {
        return PLUGIN_NAME;
    }

    @Override
    public IIntruderPayloadGenerator createNewInstance(IIntruderAttack iIntruderAttack) {
        return this;
    }

    @Override
    public void processHttpMessage(int i, boolean b, IHttpRequestResponse iHttpRequestResponse) {
        if (b){
            if (Config.AUTOXFFVALUE.equals("$RandomIp$")){
                Utils.addfakeip(iHttpRequestResponse,Utils.getRandomIp());
            }else {
                Utils.addfakeip(iHttpRequestResponse,Config.AUTOXFFVALUE);
            }
        }
    }
}
