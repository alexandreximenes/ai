package com.ia.poc_ia_2.domain;

import java.util.List;

public record Country(String name, List<String> states, List<String> cities) {
}
