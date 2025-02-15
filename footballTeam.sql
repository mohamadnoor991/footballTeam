PGDMP  2                     }            footballTeam    17.2    17.2                 0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false                       1262    16388    footballTeam    DATABASE     �   CREATE DATABASE "footballTeam" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "footballTeam";
                     postgres    false            �            1259    16404    player    TABLE     �   CREATE TABLE public.player (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    "position" character varying(255) NOT NULL
);
    DROP TABLE public.player;
       public         heap r       postgres    false            �            1259    16403    player_id_seq    SEQUENCE     �   CREATE SEQUENCE public.player_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.player_id_seq;
       public               postgres    false    218                       0    0    player_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.player_id_seq OWNED BY public.player.id;
          public               postgres    false    217            �            1259    16411    team    TABLE     �   CREATE TABLE public.team (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    acronym character varying(255),
    players integer[] NOT NULL,
    budget numeric(38,2) NOT NULL
);
    DROP TABLE public.team;
       public         heap r       postgres    false            �            1259    16410    team_id_seq    SEQUENCE     �   CREATE SEQUENCE public.team_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.team_id_seq;
       public               postgres    false    220                       0    0    team_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.team_id_seq OWNED BY public.team.id;
          public               postgres    false    219            �            1259    16478    team_players    TABLE     a   CREATE TABLE public.team_players (
    team_id bigint NOT NULL,
    player_id bigint NOT NULL
);
     DROP TABLE public.team_players;
       public         heap r       postgres    false            `           2604    16419 	   player id    DEFAULT     f   ALTER TABLE ONLY public.player ALTER COLUMN id SET DEFAULT nextval('public.player_id_seq'::regclass);
 8   ALTER TABLE public.player ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    217    218    218            a           2604    16428    team id    DEFAULT     b   ALTER TABLE ONLY public.team ALTER COLUMN id SET DEFAULT nextval('public.team_id_seq'::regclass);
 6   ALTER TABLE public.team ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    219    220    220            �          0    16404    player 
   TABLE DATA           6   COPY public.player (id, name, "position") FROM stdin;
    public               postgres    false    218   ;       �          0    16411    team 
   TABLE DATA           B   COPY public.team (id, name, acronym, players, budget) FROM stdin;
    public               postgres    false    220   �       �          0    16478    team_players 
   TABLE DATA           :   COPY public.team_players (team_id, player_id) FROM stdin;
    public               postgres    false    221   �                  0    0    player_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.player_id_seq', 31, true);
          public               postgres    false    217                       0    0    team_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.team_id_seq', 11, true);
          public               postgres    false    219            c           2606    16421    player player_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.player
    ADD CONSTRAINT player_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.player DROP CONSTRAINT player_pkey;
       public                 postgres    false    218            e           2606    16430    team team_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.team
    ADD CONSTRAINT team_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.team DROP CONSTRAINT team_pkey;
       public                 postgres    false    220            f           2606    16481 (   team_players fk3mmudt6u8a3oodc5uycfbnic4    FK CONSTRAINT     �   ALTER TABLE ONLY public.team_players
    ADD CONSTRAINT fk3mmudt6u8a3oodc5uycfbnic4 FOREIGN KEY (player_id) REFERENCES public.player(id);
 R   ALTER TABLE ONLY public.team_players DROP CONSTRAINT fk3mmudt6u8a3oodc5uycfbnic4;
       public               postgres    false    218    221    4707            g           2606    16486 (   team_players fkc9igy2kys82rwa80px3q0usqa    FK CONSTRAINT     �   ALTER TABLE ONLY public.team_players
    ADD CONSTRAINT fkc9igy2kys82rwa80px3q0usqa FOREIGN KEY (team_id) REFERENCES public.team(id);
 R   ALTER TABLE ONLY public.team_players DROP CONSTRAINT fkc9igy2kys82rwa80px3q0usqa;
       public               postgres    false    220    221    4709            �   �  x�}�Kn�@���)�,9n��4,+��ȪZ�جF3.G��ިw�.+��4��r�SXs��LA12�9��$�/±a��<�Ά�º���
�P�}fr�$��'�-;�����
n���`�8���5�	|�*���ϐ���ki;OÝW�U���Ct��I����g�����F1+��_�s���Gn�)|�M.!�8�C:�GR��ֱh
�؆��u�A��<�Ic�t��!V��t��µf�S�=X��t&Guu�v�w-�����P|�����ݒx��Fֲ
�2�'����@�|c��^���i)>8���.a������1����_-��0�L��j����z#G6�
<���Q��V�՞��vXL'���<zKUq�=kc�o��z�'i?.�$���m      �   �   x�E�Mj�0���)t�!x��m�t�@pgӍZ�D4�[ف�WP�4}�y��u;���hv��$%38���*����{�+N�{���)И���R>h��9��Zc��	qJ�ͨ���Ko����;c�X��I�b��X{�n���Y�Y.A}Q�/ۻA��F}ĔRF��lq)6��*�w�46L�T��S�k/w�����%l̉�2���*�vTNFo?M��6�II2!��v,�}[EQ���Z�      �   N   x�-���0C�3�
Bإ��Q[��?��9܂m��"�ے;H���|��6?��E邸\�!�D`~J4��.z�     