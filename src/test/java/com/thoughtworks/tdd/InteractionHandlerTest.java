package com.thoughtworks.tdd;

import com.thoughtworks.exceptions.NoParkingSpacesException;
import com.thoughtworks.exceptions.UnavailableCertificateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InteractionHandlerTest {
    private InteractionHandler handler;
    private ByteArrayOutputStream byteOut;

    @BeforeEach
    private void init(){
        this.handler = new InteractionHandler();
        this.byteOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.byteOut));
    }

    @Test
    public void should_display_main_screen_and_return_choice() throws IOException {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        PrintStream printStream = new PrintStream(byteOut);

        int choice = handler.showMainScreenAndGetChoice();

        assertThat(new String(byteOut.toByteArray()), is("1. 停车\n2. 取车\n请输入您要进行的操作："));
        assertThat(choice, is(1));
    }

}
