package com.lookfor.yanaorental.services;

/**
 * Сервис для работы с электронной почтой
 */
public interface EmailService {
    /**
     * Отправка письма на электронную почту
     * @param recipientAddress Адрес получателя
     * @param subject Тема письма
     * @param text Содержание письма
     * @param html Поддержка разметки HTML
     */
    void send(String recipientAddress, String subject, String text, boolean html);

    /**
     * Отправка письма, содержащего обычный текст
     * @param recipientAddress Адрес получателя
     * @param subject Тема письма
     * @param text Содержание письма
     */
    default void send(String recipientAddress, String subject, String text) {
        send(recipientAddress, subject, text, false);
    }
}
