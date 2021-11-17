# User Guide

## Getting Started

### Item Requirements

* Serial Number
  * A serial number must follow the pattern `A-XXX-XXX-XXX`
  * Where `A` is a letter and `X` is a letter or a number
* Name
  * A Name must be  between `2-256` characters long
* Value
  * A value must be a number `>= 0`

## How to use Items

### Creating a new Item

To create a new item, you must fill in each of the required text fields
in the bottom left for the serial number, name, and value respectively.

After you have filled those out, you may either hit enter in one of the fields or hit the `Add Item` button.
If you have filled these out correctly, your item will be added to the inventory. If you gave invalid data,
You will see an error message pop up with the invalid field

### Editing an Item

To edit and item, click on it in the table, then click the `Edit Item` button. This will take the item out of
the table and import its contents to the bottom left text fields, here you can change its contents then add the
item again.

### Removing an Item

To remove an item, simply click on it in the table to highlight it, then click the `Remove Item` button

## Manipulating the Table

### Sorting the Table

To sort the table, simply click on either of the 3 headers, `Serial Number`, `Name`, or `Value` to sort by
that column

### Clearing all Items

To clear all items from the table, click the `Clear All` button. You will see the table now becomes empty.

### Searching

To search for an item, type your query into the top search bar. This will filter the table by your query

This will search by `Serial Number`, `Name`, and `Value`

```
This is not case sensitive
You can make edits to the table while searching, and it will be reflected when the search query is removed.
```

## Saving / Loading

### Supported File Types

TSV, HTML, JSON

### Saving

To save, click the `Save` button and a file browser will appear. Choose your location and file type to save
your inventory

### Loading 

To load a saved inventory, click the `Load` button, a file browser will appear. Choose your saved inventory to
import it into your table

```
This will wipe what is currently in the table
```

