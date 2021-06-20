using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TuernOn : MonoBehaviour
{
    //Animator anim;
    Animation a;
    void Start()
    {
        a = GetComponent<Animation>();
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Space)) {
            a.Play();
        }
    }
}
