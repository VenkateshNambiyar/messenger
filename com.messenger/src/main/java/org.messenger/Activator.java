package org.messenger;

import org.messenger.message.view.ContactView;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) {
        System.out.println("Messenger");
        final ContactView contactView = new ContactView();

        contactView.selectMessenger();System.out.println("Starting the bundle");
    }

    public void stop(BundleContext context) {
        System.out.println("Stopping the bundle");
    }

}