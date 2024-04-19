package com.example.a402005776_contactapp;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public ContactDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    public void addContact(ContactModel contact) {
        ContentValues values = new ContentValues();
        values.put("Name", contact.getName());
        values.put("Email", contact.getEmail());
        values.put("Phone", contact.getPhoneNumber());
        values.put("Birthday", contact.getBirthday());
        database.insert("Contacts", null, values);
    }

    public List<ContactModel> getAllContacts() {
        List<ContactModel> contactsList = new ArrayList<>();
        Cursor cursor = database.query("Contacts", null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ContactModel contact = cursorToContact(cursor);
                contactsList.add(contact);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return contactsList;
    }
    public void updateContact(ContactModel contact) {
        ContentValues values = new ContentValues();
        values.put("Name", contact.getName());
        values.put("Email", contact.getEmail());
        values.put("Phone", contact.getPhoneNumber());
        values.put("Birthday", contact.getBirthday());
        database.update("Contacts", values, "ID = ?", new String[] { String.valueOf(contact.getId()) });
    }

    public void deleteContact(int contactId) {
        database.delete("Contacts", "ID = ?", new String[] { String.valueOf(contactId) });
    }
    public ContactModel getContactById(int contactId) {
        Cursor cursor = database.query("Contacts", null, "ID = ?", new String[] { String.valueOf(contactId) }, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            ContactModel contact = cursorToContact(cursor);
            cursor.close();
            return contact;
        }

        return null;
    }

    @SuppressLint("Range")
    private ContactModel cursorToContact(Cursor cursor) {
        ContactModel contact = new ContactModel();
        contact.setId(cursor.getInt(cursor.getColumnIndex("ID")));
        contact.setName(cursor.getString(cursor.getColumnIndex("Name")));
        contact.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
        contact.setPhoneNumber(cursor.getString(cursor.getColumnIndex("Phone")));
        contact.setBirthday(cursor.getString(cursor.getColumnIndex("Birthday")));
        return contact;
    }
}
