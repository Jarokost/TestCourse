package org.example.order;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionOrderTest {

    @Test
    void callingBackupWithoutCreatingAFileFirstShouldThrowException() throws IOException {

        //given
        OrderBackup orderBackup = new OrderBackup();

        //when
        assertThrows(IOException.class, () -> orderBackup.backupOrder(new Order()));
    }
}
