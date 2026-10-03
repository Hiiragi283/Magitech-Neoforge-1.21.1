package net.stln.magitech.helper;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public final class StreamHelper {
    private StreamHelper() {
    }

    /**
     * ランダムな値を返します。
     *
     * @param list   要素の一覧
     * @param random 使用する乱数
     * @param <T>    リストの要素のクラス
     * @return ランダムな値がある場合は{@link Optional#of(Object)}，それ以外の場合は{@link Optional#empty()}
     */
    public static @NotNull <T> Optional<T> findRandom(@NotNull List<T> list, RandomSource random) {
        return findRandom(list.stream(), random, list.size());
    }

    /**
     * ランダムな値を返します。
     *
     * @param holderSet 要素の一覧
     * @param random    使用する乱数
     * @param <T>       リストの要素のクラス
     * @return ランダムな値がある場合は{@link Optional#of(Object)}，それ以外の場合は{@link Optional#empty()}
     */
    public static @NotNull <T> Optional<Holder<T>> findRandom(@NotNull HolderSet<T> holderSet, RandomSource random) {
        return findRandom(holderSet.stream(), random, holderSet.size());
    }

    /**
     * ランダムな値を返します。
     *
     * @param stream 要素の一覧
     * @param random 使用する乱数
     * @param <T>    リストの要素のクラス
     * @return ランダムな値がある場合は{@link Optional#of(Object)}，それ以外の場合は{@link Optional#empty()}
     */
    public static <T> @NotNull Optional<T> findRandom(@NotNull Stream<T> stream, RandomSource random, int size) {
        return stream.skip(random.nextInt(size)).findFirst();
    }
}
