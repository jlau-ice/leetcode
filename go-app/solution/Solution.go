package solution

type ListNode struct {
	Val  int
	Next *ListNode
}

// addTwoNumbers 2
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	d := &ListNode{
		Val:  -1,
		Next: nil,
	}
	m := d
	curry := 0
	for l1 != nil || l2 != nil || curry != 0 {
		var a = 0
		var b = 0
		if l1 == nil {
			a = 0
		} else {
			a = l1.Val
			l1 = l1.Next
		}
		if l2 == nil {
			b = 0
		} else {
			b = l2.Val
			l2 = l2.Next
		}
		sum := a + b + curry
		curry = sum / 10
		m.Next = &ListNode{
			Val:  sum % 10,
			Next: nil,
		}
		m = m.Next

	}
	return d.Next
}

// detectCycle 142
func detectCycle(head *ListNode) *ListNode {
	set := make(map[*ListNode]struct{})
	for head != nil {
		_, ok := set[head]
		if ok {
			return head
		} else {
			set[head] = struct{}{}
		}
		head = head.Next
	}
	return nil
}
