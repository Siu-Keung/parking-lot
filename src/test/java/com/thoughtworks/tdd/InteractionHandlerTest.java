package com.thoughtworks.tdd;

import com.thoughtworks.exceptions.IllegalCommandException;
import com.thoughtworks.exceptions.NoParkingSpacesException;
import com.thoughtworks.exceptions.UnavailableCertificateException;
import jdk.internal.util.xml.impl.Input;
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
import static org.junit.jupiter.api.Assertions.fail;
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

    //将str模拟成控制台输入
    private void simulateConsoleInput(String str){
        InputStream in = new ByteArrayInputStream(str.getBytes());
        System.setIn(in);
    }

    @Test
    public void should_display_main_screen_and_return_choice() throws IOException {
        simulateConsoleInput("1");

        int choice = handler.showMainScreenAndGetChoice();

        assertThat(new String(byteOut.toByteArray()), is("1. 停车\n2. 取车\n请输入您要进行的操作："));
        assertThat(choice, is(1));
    }

    @Test
    public void should_throw_exception_when_given_available_command(){
        simulateConsoleInput("3");

        try {
            handler.showMainScreenAndGetChoice();
        }catch (IllegalCommandException e){
            return;
        }
        fail("输入无效指令应当抛异常！");
    }

    @Test
    public void should_show_prompt_and_return_car_number(){
        simulateConsoleInput("粤T4658");

        String input = handler.displayPromptAndGetCarNum();

        assertThat(new String(byteOut.toByteArray()), is("请输入车牌号："));
        assertThat(input, is("粤T4658"));
    }

    @Test
    public void should_print_park_successfully(){
        handler.displayParkSuccessfully("40b83c35-5465-47b4-8854-aaf3517b6f95");

        assertThat(new String(byteOut.toByteArray()), is("停车成功，您的小票是：\n40b83c35-5465-47b4-8854-aaf3517b6f95"));
    }


}
