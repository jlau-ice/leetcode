package main

import (
	"sort"
)

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

// removeNthFromEnd 19
func removeNthFromEnd(head *ListNode, n int) *ListNode {
	cur := &ListNode{
		Val:  -1,
		Next: head,
	}
	fast, slow := cur, cur
	for i := 0; i <= n; i++ {
		fast = fast.Next
	}
	for fast != nil {
		fast = fast.Next
		slow = slow.Next
	}
	slow.Next = slow.Next.Next
	return cur.Next
}

// swapPairs 24
func swapPairs(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	dummy := &ListNode{
		Next: head,
	}
	temp := dummy
	for temp != nil && temp.Next != nil && temp.Next.Next != nil {
		node1 := temp.Next
		node2 := node1.Next

		temp.Next = node2
		node1.Next = node2.Next
		node2.Next = node1

		temp = node1
	}
	return dummy.Next
}

// twoSum 1
func twoSum(nums []int, target int) []int {
	myMap := make(map[int]int)
	for i := 0; i < len(nums); i++ {
		a := target - nums[i]
		_, ok := myMap[a]
		if ok {
			return []int{myMap[a], i}
		} else {
			myMap[nums[i]] = i
		}
	}
	return nil
}

// longestConsecutive 128
func longestConsecutive(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	if len(nums) == 1 {
		return 1
	}
	mySet := make(map[int]struct{})
	for _, num := range nums {
		mySet[num] = struct{}{}
	}
	size := len(mySet)
	newNums := make([]int, size)
	i := 0
	for key := range mySet {
		newNums[i] = key
		i++
	}
	sort.Ints(newNums)
	maxSize := 1
	maxI := 1
	for i := 0; i < len(newNums)-1; i++ {
		if checkLink(newNums[i], newNums[i+1]) {
			maxSize++
		} else {
			maxI = isMax(maxSize, maxI)
			maxSize = 1
		}
	}
	return isMax(maxI, maxSize)
}

func checkLink(a int, b int) bool {
	return b == a+1
}

func isMax(a int, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

// longestConsecutive2 128 第二种解法
func longestConsecutive2(nums []int) int {
	if len(nums) == 0 || len(nums) == 1 {
		return len(nums)
	}
	mySet := make(map[int]struct{})
	for _, value := range nums {
		mySet[value] = struct{}{}
	}
	ans := 1
	for value := range mySet {
		if _, find := mySet[value-1]; !find {
			count := 1
			for _, ok := mySet[value+1]; ok; _, ok = mySet[value+1] {
				count++
				value++
			}
			ans = max(ans, count)
		}
	}
	return ans
}
