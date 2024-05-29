package classes;

public class RowListener implements TableRowSelectionListener {
    int selectedRow = -1;
    @Override
    public void onRowSelected(int row){
        selectedRow = row;
        GetSelectedRow();
    }
    public int GetSelectedRow(){
        return selectedRow;
    }
}
