package net.stln.magitech.util;

import net.minecraft.core.NonNullList;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamUtil {
    public static <T> Collector<T, List<T>, NonNullList<T>> nonNullListCollector() {
        return Collectors.collectingAndThen(Collectors.toList(), NonNullList::copyOf);
    }
}
