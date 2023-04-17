package br.com.nudemo.ces.common.util;

import lombok.NoArgsConstructor;

import java.text.Normalizer;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.text.Normalizer.Form.NFKD;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class StringUtil {

    private static final Pattern ONLY_DIGITS_PATTERN = Pattern.compile("\\d+");
    private static final Pattern NON_ASCII_PATTERN = Pattern.compile("[^\\p{ASCII}]");
    private static final String EMPTY = "";

    public static String removeAccents(final String input) {
        return Optional.ofNullable(input)
                .map(value -> Normalizer.normalize(value, NFKD))
                .map(value -> NON_ASCII_PATTERN.matcher(value).replaceAll(EMPTY))
                .orElse(null);
    }

    public static String removeNonDigits(final String input) {
        return Optional.ofNullable(input)
                .map(String::trim)
                .map(ONLY_DIGITS_PATTERN::matcher)
                .map(value -> value.results().map(MatchResult::group).collect(Collectors.joining()))
                .orElse(null);
    }

}
