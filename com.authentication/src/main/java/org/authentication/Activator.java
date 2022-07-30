package org.authentication;

import org.authentication.authentication.view.AuthenticationView;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {

    public void start(BundleContext context) {
        System.out.println("Welcome to Messenger");
        final AuthenticationView authenticationView = new AuthenticationView();

        authenticationView.selectAuthentication();
    }

    public void stop(BundleContext context) {
        System.out.println("Stopping the bundle");
    }

}