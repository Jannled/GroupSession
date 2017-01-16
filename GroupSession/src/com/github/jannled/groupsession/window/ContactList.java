package com.github.jannled.groupsession.window;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.github.jannled.groupsession.user.Contact;
import com.github.jannled.window.Textfield;

public class ContactList
{
	ArrayList<Contact> contacts = new ArrayList<Contact>();
	JPanel panel;
	private Textfield searchField;
	private JButton btnSearch;
	private JPanel pnlContacts;
	
	public ContactList()
	{
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSearch = new JPanel();
		panel.add(pnlSearch, BorderLayout.NORTH);
		pnlSearch.setLayout(new BorderLayout(0, 0));
		
		searchField = new Textfield(ResourceBundle.getBundle("com.github.jannled.groupsession.window.messages").getString("ContactList.searchField.hint"));
		pnlSearch.add(searchField, BorderLayout.CENTER);
		searchField.setColumns(10);
		
		btnSearch = new JButton(ResourceBundle.getBundle("com.github.jannled.groupsession.window.messages").getString("ContactList.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnSearch.setSize(btnSearch.getWidth(), searchField.getHeight());
		pnlSearch.add(btnSearch, BorderLayout.EAST);
		
		pnlContacts = new JPanel();
		panel.add(pnlContacts, BorderLayout.CENTER);
	}

	public void addContact(Contact contact)
	{
		contacts.add(contact);
	}
	
	public void removeContact(Contact contact)
	{
		contacts.remove(contact);
	}
	
	public ArrayList<Contact> getContacts()
	{
		return contacts;
	}
}
