package com.team012.server;

import com.team012.server.companyPosts.converter.ListToString;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListToStringTest {

    @Test
    void test1() {
        ListToString list = new ListToString();

        List<String> list1 = List.of("a", "b", "c");
        List<String> list2 = List.of("d","e");

    }
}
