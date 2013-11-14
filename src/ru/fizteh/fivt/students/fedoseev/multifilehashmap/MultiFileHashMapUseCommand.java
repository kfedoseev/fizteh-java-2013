package ru.fizteh.fivt.students.fedoseev.multifilehashmap;

import ru.fizteh.fivt.students.fedoseev.common.AbstractCommand;

import java.io.IOException;

public class MultiFileHashMapUseCommand extends AbstractCommand<MultiFileHashMapState> {
    public MultiFileHashMapUseCommand() {
        super("use", 1);
    }

    @Override
    public void execute(String[] input, MultiFileHashMapState state) throws IOException {
        String tableName = input[0];

        if (state.getCurDir().getName().equals(tableName)) {
            System.out.println("using " + tableName);
            return;
        }

        MultiFileHashMapTable curTable = state.getCurTable();
        int changesNumber = 0;

        if (curTable != null) {
            changesNumber = curTable.getDiffSize();
        }

        if (changesNumber != 0) {
            throw new IOException(changesNumber + " unsaved changes");
        }

        if (state.getCurDir().toPath().resolve(tableName).toFile().exists()) {
            AbstractMultiFileHashMap.saveTable(curTable);

            if (curTable != null) {
                curTable.clearContentAndDiff();
            }

            state.setCurTable(tableName);
            curTable = state.getCurTable();

            AbstractMultiFileHashMap.readTableOff(curTable);

            System.out.println("using " + tableName);
        } else {
            System.out.println(tableName + " not exists");
        }
    }
}
