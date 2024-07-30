package com.truetrack.sharing.dtos;

import java.util.Date;

public record AuthResponse (String token,Date expiry) {}
