import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import Backend from 'i18next-http-backend';
import defaultEn from './locales/default/react_messages_en.json';
import defaultEs from './locales/default/react_messages_es.json';

    const resources = {
        default_en: {
            translation: defaultEn
        },
        default_es: {
            translation: defaultEs
        },
    }
    i18n.use(initReactI18next)
        .init({
            fallbackLng: 'default_en',
            keySeparator: false,
            resources: resources,
            interpolation : false
        });

export default i18n;