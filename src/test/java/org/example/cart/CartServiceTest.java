package org.example.cart;

import org.example.order.Order;
import org.example.order.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @InjectMocks
    private CartService cartService;
    @Mock
    private CartHandler cartHandler;
    @Captor
    private ArgumentCaptor<Cart> argumentCaptor;

    @Test
    void processCartShouldSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart);

        verify(cartHandler, times(1)).sendToPrepare(cart);
        verify(cartHandler, atLeastOnce()).sendToPrepare(cart);

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().getFirst().getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void processCartShouldNotSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().getFirst().getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void processCartShouldNotSendToPrepareArgumentMatchers() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPrepare(any(Cart.class));
        then(cartHandler).should(never()).sendToPrepare(any(Cart.class));
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().getFirst().getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void canHandleCartShouldReturnMultipleValues() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(true, false, false, true);

        //then
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
    }

    @Test
    void processCartShouldSendToPrepareWithLambdas() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(argThat(c -> !c.getOrders().isEmpty()))).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().getFirst().getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void processCartShouldThrowException() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> cartService.processCart(cart));

    }

    @Test
    void processCartShouldSendToPrepareWithArgumentCaptor() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        //verify(cartHandler).sendToPrepare(argumentCaptor.capture());
        then(cartHandler).should().sendToPrepare(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().getFirst().getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void shouldDoNothingWhenProcessCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

//        doNothing().when(cartHandler).sendToPrepare(cart);
        willDoNothing().given(cartHandler).sendToPrepare(cart);
//        willDoNothing().willThrow(IllegalStateException.class).given(cartHandler).sendToPrepare(cart);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().getFirst().getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void shouldAnswerWhenProcessCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        doAnswer(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            argumentCart.clearCart();
            return true;
        }).when(cartHandler).canHandleCart(cart);

//        when(cartHandler.canHandleCart(cart)).then(i -> {
//            Cart argumentCart = i.getArgument(0);
//            argumentCart.clearCart();
//            return true;
//        });
//
//        willAnswer(invocationOnMock -> {
//            Cart argumentCart = invocationOnMock.getArgument(0);
//            argumentCart.clearCart();
//            return true;
//        }).given(cartHandler).canHandleCart(cart);
//
//        given(cartHandler.canHandleCart(cart)).will(i -> {
//            Cart argumentCart = i.getArgument(0);
//            argumentCart.clearCart();
//            return true;
//        });

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrders().size(), equalTo(0));
    }

    @Test
    void deliveryShouldBeFree() {
        //given
        Cart cart = new Cart();
        cart.addOrderToCart(new Order());
        cart.addOrderToCart(new Order());
        cart.addOrderToCart(new Order());

        given(cartHandler.isDeliveryFree(cart)).willReturn(true);

        //when
        boolean isDeliveryFree = cartHandler.isDeliveryFree(cart);

        //then
        assertTrue(isDeliveryFree);
    }
}