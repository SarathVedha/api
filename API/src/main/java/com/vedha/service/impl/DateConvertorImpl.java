package com.vedha.service.impl;

import com.vedha.service.DateConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.HijrahEra;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Slf4j
@Service
@AllArgsConstructor
public class DateConvertorImpl implements DateConvertor {

    @Override
    public String convertGregorianToHijri(String gregorianDate) {

        try {

            String finalGregorianDate = gregorianDate
                    .replace("dd/MM/yyyy", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            Calendar cl = Calendar.getInstance();
            cl.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(finalGregorianDate));
            return HijrahChronology
                    .INSTANCE
                    .date(LocalDate.of(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE))).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (Exception e) {

            return e.getMessage();
        }
    }

    @Override
    public String convertHijriToGregorian(String hijriDate) {

        try {

            hijriDate = hijriDate.replace("dd/MM/yyyy", convertGregorianToHijri("dd/MM/yyyy"));

            String finalHijriDate = (hijriDate.replace(" ", "0")).replace("/", "");

            char charAt = finalHijriDate.subSequence(0, 2).charAt(0);
            String date;
            if (charAt == '0') {

                date = finalHijriDate.subSequence(1, 2).toString();
            } else {

                date = finalHijriDate.subSequence(0, 2).toString();
            }

            char charAt2 = finalHijriDate.subSequence(2, 4).charAt(0);
            String month;
            if (charAt2 == '0') {

                month = finalHijriDate.subSequence(3, 4).toString();
            } else {

                month = finalHijriDate.subSequence(2, 4).toString();
            }

            String year = finalHijriDate.subSequence(4, finalHijriDate.length()).toString();

            HijrahDate hd = HijrahChronology.INSTANCE.date(HijrahEra.AH, Integer.parseInt(year),
                    Integer.parseInt(month), Integer.parseInt(date));

            return LocalDate.from(hd).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        }catch (Exception e){

            return e.getMessage();
        }
    }
}
