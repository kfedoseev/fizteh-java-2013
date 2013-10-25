package ru.fizteh.fivt.students.fedoseev.multifilehashmap;

import ru.fizteh.fivt.students.fedoseev.common.AbstractCommand;
import ru.fizteh.fivt.students.fedoseev.filemap.AbstractFileMap;
import ru.fizteh.fivt.students.fedoseev.filemap.FileMapGetCommand;
import ru.fizteh.fivt.students.fedoseev.filemap.FileMapState;

import java.io.IOException;

public class MultiFileHashMapGetCommand extends AbstractCommand<MultiFileHashMapState> {
    public MultiFileHashMapGetCommand() {
        super("get", 1);
    }

    @Override
    public void execute(String[] input, MultiFileHashMapState state) throws IOException {
        MultiFileHashMapTable table = state.getCurTable();

        if (table == null) {
            System.out.println("no table");
        } else {
            AbstractMultiFileHashMap.readTable(table);

            AbstractFileMap.setContent(table.getMapContent());

            FileMapGetCommand get = new FileMapGetCommand();
            get.execute(input, new FileMapState());
        }
    }
}