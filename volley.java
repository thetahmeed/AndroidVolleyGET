    
    -------------Add a single data-------------

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

                // UserInfo is the root.
                // databaseReference.push().getKey() is to get an unique key.

                databaseReference = firebaseDatabase.getReference("UserInfo").child(databaseReference.push().getKey());
                databaseReference.child("Key").setValue("Value");
               

    
    -------------Get a single data-------------
    
    databaseReference.addValueEventListener(new ValueEventListener()
            {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) 
            {
                String value = dataSnapshot.child("first_chile").child("second_child").child("final_child").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Error toast
            }
        });
