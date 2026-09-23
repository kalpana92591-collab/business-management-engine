package com.businessmanagement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AddressTest {

    // -------------------------------------------------
    // CREATE ADDRESS
    // -------------------------------------------------

    @Test
    void shouldCreateAddress() {

        Address address =
                new Address(
                        "MG Road",
                        "Bengaluru",
                        "Karnataka",
                        "560001");

        assertNotNull(address);

        assertEquals(
                "MG Road",
                address.street());

        assertEquals(
                "Bengaluru",
                address.city());

        assertEquals(
                "Karnataka",
                address.state());

        assertEquals(
                "560001",
                address.postalCode());
    }

    // -------------------------------------------------
    // RECORD EQUALITY
    // -------------------------------------------------

    @Test
    void shouldCompareEqualAddresses() {

        Address address1 =
                new Address(
                        "MG Road",
                        "Bengaluru",
                        "Karnataka",
                        "560001");

        Address address2 =
                new Address(
                        "MG Road",
                        "Bengaluru",
                        "Karnataka",
                        "560001");

        assertEquals(
                address1,
                address2);
    }

    // -------------------------------------------------
    // RECORD TOSTRING
    // -------------------------------------------------

    @Test
    void shouldDisplayAddressUsingToString() {

        Address address =
                new Address(
                        "MG Road",
                        "Bengaluru",
                        "Karnataka",
                        "560001");

        String result =
                address.toString();

        assertNotNull(result);

        assertEquals(
                "Address[street=MG Road, city=Bengaluru, state=Karnataka, postalCode=560001]",
                result);
    }
}